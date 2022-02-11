<template>
  <div class="Collections">
<el-card class="box-card">
  <div>
    <h2>所有日记</h2>
  </div>
  <el-table
   @row-dblclick="rowdblclick"
    :data="tableData.filter(data => !search || data.title.toLowerCase().includes(search.toLowerCase()))"
    style="width: 100%"
    :default-sort = "{prop: 'createTime', order: 'descending'}">
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
      <template slot="header" slot-scope="scope">
        <el-input
          v-model="search"
          size="mini"
          placeholder="输入关键字搜索"/>
      </template>
        <template slot-scope="scope">
           <!-- <el-button @click="lookriji(scope.row)" type="primary" size="mini" >查看</el-button> -->
          <el-button @click="lookriji(scope.row)" type="primary" size="mini" >查看</el-button>
		  <el-button @click="deleteriji(scope.row)" type="danger" size="mini">删除</el-button>
        </template>
      </el-table-column>
  </el-table>
</el-card>

<el-drawer
  title="查看日记内容"
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
    <!-- <el-button type="primary" @click="updateriji()">确认修改</el-button> -->
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

<script type="text/javascript">
import $ from 'jquery';
export default {
  name:"AllRiJi",
  data() {
    return {
        tableData: [],
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
        search: '',
        table:false,
         pageSize:5, // 页数
      currentPage: 1, // 当前页
      total:0,
    }
  },
  components: {

  },
   created() {
    this.queryAllRiJi()
  },
  mounted () {
    this.summernote()
  },
  methods:{
     handleCurrentChange(pageNum){
      this.currentPage=pageNum
      // console.log(this.currentPage)
       this.queryAllRiJi()
    },
     rowdblclick(row, column, cell, event){
         this.table = true
         this.queryRiJiById(row.id)
      },
      lookriji(row){
         this.table = true
         this.queryRiJiById(row.id)
      },
      queryRiJiById(id){
         this.$http.post('/RiJi/queryRiJiDetail/'+id).then(res => {
             if(res.data.code == 200){
                 this.riji = res.data.data;
                 this.riji.feelScore = res.data.data.feelScore / 2;
             }
             this.RiJiform.title = this.riji.title
             this.RiJiform.level = this.riji.feelScore
             this.summernote()
           $('#summernote').summernote('code',this.riji.url);
           }).catch(err => {
           console.log(err)
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
      queryAllRiJi(){
           let params = new URLSearchParams();

       params.append("pageSize",this.pageSize);
       params.append("pageNum",this.currentPage)
        this.$http.post("/RiJi/queryAllUserRiJi",params).then(res=>{
          if(res.data.code == 200){
             this.tableData = res.data.data.rows
             this.total=res.data.data.total
           this.currentPage=res.data.data.pageNum
          }
        }).catch(err => {
          console.log(err)
        })
      },
    cancle(){
      this.table = false;
    }
  },

}
</script>

<style scoped>
</style>
