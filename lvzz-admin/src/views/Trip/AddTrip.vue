<template>
  <div class="addTrip">
  <el-card class="box-card" style="margin-top:-20px;">
  <div slot="header" class="clearfix" >
    <h2>添加景区</h2>
  </div>
  <el-form :model="addTripForm" :rules="addTripRules" ref="addTripForm" label-width="100px" class="demo-addTripForm">
  <el-form-item label="景区名称" prop="TripName">
    <el-input v-model="addTripForm.TripName"></el-input>
  </el-form-item>
  <el-form-item label="门票价格" prop="price">
    <el-input type="number" v-model="addTripForm.price">
    </el-input>
  </el-form-item>


  <el-form-item prop="levelId" label="景区类别">
         <el-select v-model="addTripForm.levelId" clearable placeholder="请选择景区类别" style="width:150px;">
    <el-option
      v-for="item in tripLevel"
      :key="item.value"
      :label="item.name"
      :value="item.level">
    </el-option>
  </el-select>
           </el-form-item>
  <el-form-item label="联系方式" prop="phone">
    <el-input  v-model="addTripForm.phone"></el-input>
  </el-form-item>
  <el-form-item label="景区地址" prop="address">
    <el-input  v-model="addTripForm.address"></el-input>
  </el-form-item>

   <el-form-item label="景区描述" prop="info">
    <el-input type="textarea" v-model="addTripForm.info"></el-input>
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
    <el-button type="primary" @click="addTrip('addTripForm')">添加景区</el-button>
    <el-button @click="resetForm('addTripForm')">取消</el-button>
  </el-form-item>
</el-form>
</el-card>

  </div>
</template>

<script type="text/javascript">

import {cities} from '../../assets/data/form'
export default {
  name:"AddTrip",
  data() {
    return {
      addTripForm:{
          TripName: '',
          price: '',
          phone:'',
          levelId:'',
          levelName:'',
          address: '',
          picImg: '',
          info:''
      },
      cities:[],
      addTripRules:{
           TripName: [
            { required: true, message: '景区名不能为空', trigger: 'blur' },
          ],
           price: [
            { required: true, message: '门票不能为空', trigger: 'blur' },
          ],
           levelId: [
            { required: true, message: '景区类别必选', trigger: 'change' },
          ],
          phone: [
            { required: true, message: '联系方式不能为空', trigger: 'change' },
          ],
           address: [
            { required: true, message: '景区地址不能为空', trigger: 'blur' },
          ],
           picImg: [
            { required: true, message: '景区图片不能为空', trigger: 'blur' },
          ],
      },
      fileList:[],
    }
  },
  components: {

  },
  methods:{
    success(file,response){
      this.addTripForm.picImg=file.data;
      this.$refs['addTripForm'].validateField('picImg');
    },
     exceed(){
      const _this=this
      this.$message.error("只能上传一张图片")
      // _this.notify("只能上传一张房源图片","error")
    },

     addTrip(formName) {

        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$http.post("/trip/addTrip",this.addTripForm).then(res=>{
               if(res.data.code==200){
                 this.$message.success("添加成功")
                //  this.notify("添加成功","success"),
                 this.resetForm(formName);
                 this.fileList=[];
               }
            })
          } else {
            console.log('表单填写有误');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
        this.fileList=[];
      },
    //   queryDict(groupId){
    //   this.$http.get('/dict/queryDict/'+groupId).then(res=>{
    //      if(res.data.code==200){
    //        if(groupId=='rent_mode'){
    //           this.rentModeList=res.data.data
    //        }
    //         if(groupId=='Trip_type'){
    //           this.TripTypeList=res.data.data
    //        }
    //         if(groupId=='direction'){
    //           this.directionList=res.data.data
    //        }
    //      }
    //   })
    // },
  },
  created(){
    this.cities = cities
  }
}
</script>

<style scoped>
</style>
