<template>
  <div class="AllRiJi">
     <el-card style="margin-bottom:10px;margin-top:-10px;width:1000px;margin-left:90px;height:80px">
        <el-form ref="form" :model="form" label-width="80px">
       <el-row :gutter="24">
         <el-col :span="6">
           <el-form-item label="查询内容">
           <el-input v-model="form.content" style="width: 150px;"   placeholder="请输入关键字"></el-input>
          </el-form-item>
         </el-col>
         <el-col :span="4">
             <el-form-item label="日记时间">
                 <el-col :span="4">
                   <el-date-picker type="date" placeholder="选择日期" v-model="form.createTime" value-format="yyyy-MM-dd" style="width: 150px;"></el-date-picker>
                 </el-col>
             </el-form-item>
         </el-col>
         <el-col :span="8">
           <el-form-item style="margin-left:10px;">
             <el-button type="primary" @click="queryByInfo()">查询</el-button>
             <el-button type="warning" @click="resetForm()">重置</el-button>
            </el-form-item>
         </el-col>
       </el-row>
</el-form>
     </el-card>


    <el-table
    :data="tableData"
    @row-dblclick="rowdblclick"
    style="width: 80%;margin-left:80px;margin-top:30px;"
    :default-sort = "{prop: 'createTime', order: 'descending'}"
    >
    <el-table-column
      prop="createTime"
      label="日期"
      sortable
      width="180">
    </el-table-column>
    <el-table-column
      prop="title"
      label="标题"
      width="180">
    </el-table-column>
    <el-table-column
      prop="feelScore"
      label="心情/天气"
    >
     <template slot-scope="scope">
           <el-rate v-model="scope.row.feelScore" disabled></el-rate>
        </template>
    </el-table-column>

    <el-table-column
          fixed="right"
          label="操作"
          width="180">
        <template slot-scope="scope">
           <!-- <el-button @click="lookriji(scope.row)" type="primary" size="mini" >查看</el-button> -->
          <el-button @click="editriji(scope.row)" type="primary" size="mini" >修改</el-button>
		  <el-button @click="deleteriji(scope.row)" type="danger" size="mini">删除</el-button>
        </template>
      </el-table-column>
  </el-table>

  <el-drawer
  title="编辑日记内容"
  :visible.sync="table"
  direction="rtl"
  size="40%">
   <el-form ref="RiJiform" :model="RiJiform" label-width="80px">
      <el-form-item label="标题" style="margin-left:-20px;">
         <el-input  style="margin-left:20px;"  v-model="RiJiform.title"></el-input>
      </el-form-item>

      <el-form-item label="天气/心情">
        <el-rate style="margin-top:10px;"  v-model="RiJiform.level" show-text allow-half></el-rate>
      </el-form-item>

     <el-form-item label="日记内容">
    <div id="summernote">
    </div>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="updateriji()">确认修改</el-button>
    <el-button @click="cancle()">取消</el-button>
  </el-form-item>
</el-form>
</el-drawer>

   <div class="pagination" style="margin-top:25px;margin-left:70px;">
        <el-pagination
         @current-change="handleCurrentChange"
          background
          layout="total, prev, pager, next"
          :current-page="currentPage"
          :page-size="pageSize"
          :total="total">
        </el-pagination>
    </div>

  </div>
</template>

<script>

import $ from 'jquery';

export default {
  data() {
    return {
      pageSize:5, // 页数
      currentPage: 1, // 当前页
      total:0,
      userId:'',
      value5:0,
      form: {
        content: '',
        createTime: '',
        },
      tableData: [],
      table: false,
      riji:{
             content:'',
             createTime: '',
             feelScore:'',
             id:'',
             pic:'',
             title:'',
             url:'',
             userId:'',
           },
      RiJiform:{
         title:'',
         level:''
       },
    }
  },
  components: {

  },
  created () {
     this.userId = JSON.parse(window.sessionStorage.getItem("user")).id
     this.queryAllRiJi();
  },
  mounted () {
    this.summernote()
  },
  methods: {
     handleCurrentChange(pageNum){
      this.currentPage=pageNum
      // console.log(this.currentPage)
       this.queryAllRiJi()
    },
     queryAllRiJi() {
       let params = new URLSearchParams();
       params.append("userId",this.userId);
       params.append("content",this.form.content);
       params.append("createTime",this.form.createTime);
       params.append("pageSize",this.pageSize);
       params.append("pageNum",this.currentPage)

       this.$http.post('/RiJi/queryAllRiJiByInfo',params).then(res =>{
         if(res.data.code == 200) {
            this.currentPage = res.data.data.pageNum
          this.tableData = res.data.data.rows
          // console.log(this.tableData)
          this.total=res.data.data.total
            for(let item in this.tableData){
              this.tableData[item].feelScore =  this.tableData[item].feelScore / 2
              //  item.feelScore = item.feelScore / 2
            }

         }
       }).catch(err => {
          console.log(err)
        })
     },
      onSubmit() {
        console.log('submit!');
      },
      formatter(row, column) {
        return row.content;
      },
      editriji(row){
        this.table = true

        this.$http.post('/RiJi/queryRiJiDetail/'+row.id).then(res=>{
         if(res.data.code==200){
            this.riji=res.data.data
            this.riji.feelScore = res.data.data.feelScore / 2
            // console.log(this.riji)
         }
         this.RiJiform.title = this.riji.title
         this.RiJiform.level = this.riji.feelScore
        this.summernote()
         $('#summernote').summernote('code',this.riji.url);

      })
      },
       summernote(){
       $('#summernote').summernote({
         height: 300,
         lang:'zh-CN',
         focus:true,
         toolbar:[
             ['style',['bold', 'underline','italic','clear']],
             ['frontsize',['frontsize']],
             ['fontname', ['fontname']],
             ['para',['ul','ol','paragraph']],
             ['insert',['picture']]
         ],
          callbacks: {

            onChange: function(contents, $editable) {
            var text = $('<div>'+contents+'</div>').text();
             },
            onImageUpload: function(files) {
            // console.log(files[0]);
             var data = new FormData();
    	       data.append("files", files[0]);
          // data.append("userId",this.userId);
          $.ajax({
    	        data: data,
    	        type: "POST",
    	        url:"http://localhost:9001/boot/RiJi/upload",  //上传路径
    	        cache: false,
    	        contentType: false,
    	        processData: false,
    	        success: function(res) {
    	            // console.log(res);
    	            $('#summernote').summernote('insertImage', 'http://localhost:9001/boot'+res.data);
                  this.pic=res.data;
                  // console.log(this.pic);
    	        },
    	    });
	        }
          }
       });
    },
    updateriji(){
       var text=$("#summernote").summernote('code')
       if($('#summernote').summernote('isEmpty')){
           this.$message.error("内容不能为空!")
       } else{
          let result=this.sliceStr(text);
     let params = new URLSearchParams();
     params.append("id",this.riji.id);
     params.append("userId",this.riji.userId);
     params.append("title",this.RiJiform.title);
     params.append("feelScore",this.RiJiform.level * 2);
     params.append("content",text);
     if(result.length>1){
         params.append("pic",result[1].content);
      }
      params.append("url",text)
      this.$http.post('/RiJi/updateRiJi',params).then(res=>{
         if(res.data.code==200){
             this.$message.success("修改成功!")
            this.table = false;

         }else{
           this.$message.error("修改失败!请稍后重试")
         }
      }).catch(err => {
          console.log(err)
        })
       }
    },
    sliceStr(str) {
		let result = [];
		// 提取url的正则
		const reg = /<img.+?src=[\"\'](.+?)[\"\'].{0,}?>/g;
		let index = 0;

		// 针对p标签处理(浏览器端可能会有间隔,导致显示不一致)
		// 加上类名,可以在css中处理
		str = str.replace(/<p/g, '<p data-type="nodeText"')
		str.replace(reg, (value, word, position) => {
			const valueLen = value.length;
			const text = {
				type: 'text',
				content: str.slice(index, position)
			}
			const img = {
				type: 'image',
				content: word
			}
			result.push(text, img);
			index = position + valueLen;
		})
		result.push({
			type: 'text',
			content: str.slice(index)
		})
		return result
	},
      deleteriji(row){
        // console.log(row.id)
        // console.log(this.userId)
         this.$http.get('/RiJi/deleterijiById',{params:{
              id: row.id,
              userId:this.userId
         }}).then(res => {
            if(res.data.code == 200){
                this.$message.success("删除成功!")
                this.queryAllRiJi()
            }else{
              this.$message.error("删除失败!请稍后重试")
            }
         }).catch(err => {
           console.error(err)
         })
      },
      lookriji(row){
        // this.$router.push({path:'/rijiDetail',query:{id:row.id}})
        //  _this.$router.push({path:'/'+_this.Form.type,query:{infolist:infolist}
        //          _this.$router.push({name:'/'+_this.Form.type,params:{infolist:infolist}
      },
      rowdblclick(row, column, cell, event){
         this.$router.push({path:'/rijiDetail',query:{id:row.id}})
      },
      queryByInfo(){
           let params = new URLSearchParams();
       params.append("userId",this.userId);
       params.append("content",this.form.content);
      console.log(this.form.createTime)
       params.append("createTime",this.form.createTime);
       params.append("pageSize",this.pageSize);
       params.append("pageNum",1)
       this.$http.post('/RiJi/queryAllRiJiByInfo',params).then(res =>{
         if(res.data.code == 200) {
            this.currentPage = res.data.data.pageNum
          this.tableData = res.data.data.rows
          // console.log(this.tableData)
          this.total=res.data.data.total
            for(let item in this.tableData){
               this.tableData[item].feelScore =  this.tableData[item].feelScore / 2
            }

         }
       }).catch(err => {
          console.log(err)
        })
      },
      cancle()
      {
        this.table = false
      },
      resetForm(){
         this.form.content='',
         this.form.createTime=''
      }

  }
}
</script>

<style lang="scss"scoped>
</style>
