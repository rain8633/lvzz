<template>
  <div class="HouseList">

  <!-- 编辑景点信息 -->
   <el-dialog title="编辑景点信息" :visible.sync="editTripFormVisible" @closed="editClose()">

<el-form :model="editTripForm" :rules="editTripRules" ref="editTripForm" label-width="100px">
  <el-form-item label="景点名称" prop="tripName">
    <el-input v-model="editTripForm.tripName"></el-input>
  </el-form-item>
  <el-form-item label="门票价格" prop="price">
    <el-input type="number" v-model="editTripForm.price"></el-input>
  </el-form-item>
  <el-form-item label="景区地址" prop="address">
   <el-input  v-model="editTripForm.address"></el-input>
  </el-form-item>
  <el-form-item label="景区类别" prop="levelId">
<!-- <el-radio-group v-model="editTripForm.levelName"> -->
       <!-- <el-radio v-for="(item,index) in tripLevel" :label="item.name" :key="index">{{item.name}}</el-radio> -->
       <el-select v-model="editTripForm.levelId" clearable  style="width:150px;">
    <el-option
      v-for="item in tripLevel"
      :key="item.name"
      :label="item.name"
      :value="item.level">
    </el-option>
  </el-select>
    <!-- </el-radio-group> -->
  </el-form-item>
<el-form-item label="联系方式" prop="phone">
   <el-input v-model="editTripForm.phone"></el-input>
  </el-form-item>
  <el-form-item label="景区描述" prop="info">
  <el-input
  type="textarea"
  placeholder="请输入内容"
  v-model="editTripForm.info"
  maxlength="30"
  show-word-limit
></el-input>
  </el-form-item>
<el-form-item label="景区图片" prop="picImg" style="display:table">
     <el-upload
       name="file"
      :action="path+'/trip/upload'"
      :on-success="success"
      :limit="1"
      :on-exceed="exceed"
      :file-list="fileList"
      list-type="picture">
  <el-button  size="small" type="primary" style="margin-right:25px;">上传景区图片</el-button>
  <!-- <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div> -->
</el-upload>
    </el-form-item>

  <el-form-item>
    <el-button type="primary" @click="updateHouse('editTripForm')">确认修改</el-button>
    <el-button @click="editTripFormVisible=false">取消</el-button>
  </el-form-item>
</el-form>
   </el-dialog>

<!-- 展示景点列表 -->
    <el-card style="margin-bottom:20px;margin-top:-20px;width:1380px;margin-left:20px;">
     <el-form :model="queryForm" ref="queryForm">
       <el-row :gutter="24">
         <el-col :span="4">
           <el-form-item prop="tripName">
           <el-input v-model="queryForm.tripName" placeholder="请输入景点名" style="width:180px;"></el-input>
           </el-form-item>
           </el-col>
           <el-col :span="2">
           <el-form-item prop="min">
         <el-input v-model="queryForm.min" type="number" placeholder="最小价" style="width:80px;"></el-input>
           </el-form-item>
         </el-col>
         <el-col :span="2">
         <el-form-item prop="max">

        <el-input v-model="queryForm.max" type="number" placeholder="最大价" style="width:80px;"></el-input>
           </el-form-item>
         </el-col>
         <el-col :span="4">
           <el-form-item prop="address">
         <el-select v-model="queryForm.address" clearable placeholder="请选择地区" style="width:150px;">
    <el-option
      v-for="item in cities"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
           </el-form-item>
         </el-col>
       <el-col :span="4">
           <el-form-item prop="levelName">
         <el-select v-model="queryForm.levelName" clearable placeholder="请选择景区类别" style="width:150px;">
    <el-option
      v-for="item in tripLevel"
      :key="item.value"
      :label="item.name"
      :value="item.name">
    </el-option>
  </el-select>
           </el-form-item>
         </el-col>

         <el-col :span="4">
      <el-button type="primary" @click="queryHouse()">查询</el-button>
      <el-button type="warning" @click="resetForm()">重置</el-button>
    </el-col>
       </el-row>

     </el-form>
    </el-card>
  <el-row >
  <el-col :span="6" v-for="(trip,index) in tripList" :key="index" >
    <el-card shadow="hover" :body-style="{ padding: '0px' }" style="width:300px;height:380px">
      <img :src="path+trip.picImg" class="image" height="290px" width="300px">
      <div style="padding: 14px;">
        <span>{{trip.tripName}}</span>
        <span style="margin-left:8px;color: orange;">{{trip.price}} 元/人</span>
        <div class="bottom clearfix">
          <!-- <time class="time">{{ trip.publishTime }}</time><br> -->
          <el-row :gutter="20">
          <el-col :span="4"> <el-button type="text" class="button" style="color:blue" @click="editHouse(trip.id)">编辑</el-button></el-col>
          <el-col :span="4"> <el-button type="text" class="button" style="color:red" @click="deleteHouse(trip.id)">删除</el-button> </el-col>
        </el-row>
        </div>
      </div>
    </el-card>
  </el-col>
</el-row>
 <el-pagination style="margin-top:30px;margin-left:5px;"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pageNum"
      :page-sizes="[4,8,16,32]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
  </div>
</template>

<script type="text/javascript">
import { mixin } from "../../mixins";
import {cities} from '../../assets/data/form'
import {tripLevel} from '../../assets/data/tripList'
export default {
  name:"TripList",
  mixins: [mixin],
  data() {
    return {
      pageSize: 0,
      total: 0,
      tripList:[],
      tripLevel:[],
      cities:[],
      flag:-1,
      editTripFormVisible:false,
      total:0,
     pageSize:4,
      pageNum:1,
      queryForm:{
       tripName: "",
       address:'',
       levelName:'',
       min:'',
       max:''
      },
      fileList:[],
      editTripForm:{
       id:'',
       tripName: "",
       levelId:'',
       levelName:'',
       phone:'',
       price:'',
       address:'',
       picImg:'',
       info:''
      },
       user: {
       id:'',
       userName:'',
       password:'',
       pic:'',

     },
      shoucangList:[],
      editTripRules:{
          tripName: [
            { required: true, message: '景点名称不能为空', trigger: 'blur' },
          ],
          price:[
            { required: true, message: '门票价格不能为空', trigger: 'blur'}
          ],
           address: [
            { required: true, message: '地址不能为空', trigger: 'blur' },
          ],
          levelId: [
            { required: true, message: '景区类别必选', trigger: 'change'}
          ],
           phone: [
            { required: true, message: '联系方式必选', trigger: 'change' },
          ],
           picImg: [
            { required: true, message: '景区图片不能为空', trigger: 'blur' },
          ],
      },
    }
  },
  components: {

  },
  methods: {
    resetForm(){
     this.queryForm={
       tripName: "",
        min:'',
        max:'',
        address:''
      },
      this.queryHouse()
    },

    queryHouse(){
     this.$http.get('/trip/queryAllTrip',{params:{
       pageSize:this.pageSize,
       pageNum:this.pageNum,
       tripName:this.queryForm.tripName,
       levelName:this.queryForm.levelName,
       address:this.queryForm.address,
       min:this.queryForm.min,
       max:this.queryForm.max,
     }}).then(res=>{
        if(res.data.code==200){
           this.tripList=res.data.data.rows
           this.total=res.data.data.total
           this.pageNum=res.data.data.pageNum
        }
     })
    },
    handleSizeChange(pageSize){
       this.pageSize=pageSize
       this.queryHouse()
    },
    handleCurrentChange(pageNum){
      this.pageNum=pageNum
      this.queryHouse()
    },

    editHouse(tripId){

      this.$http.post('/trip/queryTripById/'+tripId).then(res=>{
        if(res.data.code==200){
          this.fileList=[]
             this.editTripFormVisible=true
             this.editTripForm=res.data.data
             let pathPic={}
             pathPic.url=this.path+res.data.data.picImg
             this.fileList.push(pathPic)
        }
      })
    },
    // isShoucang(houseId){
    //    for(let i in this.shoucangList){
    //       if(houseId==i){
    //         return true;
    //       }
    //    }
    // },
        updateHouse(formName){
          console.log(this.editTripForm)
          this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$http.post('/trip/updateTripById',this.editTripForm).then(res=>{
            if(res.data.code == 200){
              this.$message.success("修改成功")
              this.editTripFormVisible=false,
              this.queryHouse()
            }else{
              this.$message.error("修改失败")
            }
            })
          } else {
            this.$message.error("校验失败")
            return false;
          }
        });
    },
    deleteHouse(houseId){

 this.$confirm('是否确认删除该景点?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(()=>{
      this.$http.post('/trip/deleteTripById/'+houseId).then(res=>{
        if(res.data.code==200){
          this.$message.success("删除成功!")
             this.queryHouse()
        }else{
          this.$message.error("删除失败!")
        }
      })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
    },
    addLove(id,index){
      let userId=JSON.parse(window.sessionStorage.getItem("user")).id;
      this.$http.get('/trip/addLoveTrip/',{params:{
        houseId:id,
        userId:userId
      }}).then(res =>{
        if(res.data.code==200){
          this.flag=index
            this.$message.success("收藏成功!")
            this.queryHouse()
        }
      })
    },
    // queryShoucang(){
    //    let userId=JSON.parse(window.sessionStorage.getItem("user")).id;
    //     this.$http.get('/house/queryShouCang/',{params:{
    //     userId:userId
    //   }}).then(res =>{
    //     if(res.data.code==200){
    //         this.shoucangList=res.data.data

    //         console.log(this.shoucangList)
    //         console.log("查询成功")
    //     }
    //   })
    // },
    editClose(){
      this.fileList=[]
      this.editTripFormVisible=false
    },
    success(file,response){
      this.editTripForm.picImg=file.data;
      this.$refs['editTripForm'].validateField('picImg');
    },
     exceed(){
      const _this=this
      // this.$message.error("只能上传一张图片")
      _this.notify("只能上传一张房源图片,需删除重新上传","error")
    },
     handleupdate(id) {//修改租赁状态
         this.$http.post('/trip/updateStatus/'+id+'/'+0).then(res=>{
            if(res.data.code==200){
                this.$message.success("修改成功")
                this.queryHouse()
            }
            else{
              this.$message.error("修改失败!")
            }
         })
      }

  },
  created(){
     let user=JSON.parse(window.sessionStorage.getItem("user"));
   if(user != null){
     this.user=user;
   }
     this.cities = cities
     this.tripLevel = tripLevel
     this.queryHouse()
  }
}


</script>

<style scoped>
  .time {
    font-size: 13px;
    color: #999;
  }

  /deep/input::-webkit-outer-spin-button,
  /deep/input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    appearance: none;
    margin: 0;
  }

</style>
