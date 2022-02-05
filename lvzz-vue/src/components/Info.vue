<template>
  <div class="info">
    <p class="title">编辑个人资料</p>
    <hr/>
    <div class="personal">
      <el-form :model="registerForm" ref="registerForm" class="demo-ruleForm" label-width="80px">
        <el-form-item prop="userName" label="用户名">
          <el-input v-model="registerForm.userName" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码">
          <el-input type="password" placeholder="密码" v-model="registerForm.password"></el-input>
        </el-form-item>
        <!-- <el-form-item label="性别">
          <el-radio-group v-model="registerForm.sex">
            <el-radio :label="0">女</el-radio>
            <el-radio :label="1">男</el-radio>
          </el-radio-group>
        </el-form-item> -->
        <el-form-item prop="phone" label="手机">
          <el-input  placeholder="手机" v-model="registerForm.phone" ></el-input>
        </el-form-item>
        <!-- <el-form-item prop="email" label="邮箱">
          <el-input v-model="registerForm.email" placeholder="邮箱"></el-input>
        </el-form-item> -->
        <!-- <el-form-item prop="birth" label="生日">
          <el-date-picker type="date" placeholder="选择日期" v-model="registerForm.birth" style="width: 100%;"></el-date-picker>
        </el-form-item>
        <el-form-item prop="introduction" label="签名">
          <el-input  type="textarea" placeholder="签名" v-model="registerForm.introduction" ></el-input>
        </el-form-item>
        <el-form-item prop="location" label="地区">
          <el-select v-model="registerForm.location" placeholder="地区" style="width:100%">
            <el-option
              v-for="item in cities"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item> -->
      </el-form>
    <div class="btn">
      <div @click="saveMsg()">保存</div>
      <div @click="goback">取消</div>
    </div>
    </div>
</div>
</template>

<script>
import { mapGetters } from 'vuex'
// import { cities } from '../assets/data/form'
// import { updateUserMsg, getUserOfId } from '../api/index'

export default {
  name: 'info',
  data: function () {
    return {
      userId:'',
      registerForm: { // 注册
        userName: '',
        password: '',
        // sex: '',
        phone: '',
        // email: '',
        // birth: '',
        // introduction: '',
        // location: ''
      },
      // cities: []
    }
  },
  // computed: {
  //   ...mapGetters([
  //     'userId'
  //   ])
  // },
  created () {
     this.userId=JSON.parse(window.sessionStorage.getItem('user')).id;
    //  console.log(this.userId)
     this.getMsg(this.userId)
  },
  mounted () {

  },
  methods: {
    getMsg (id) {
     this.$http.post("/user/queryUserById/"+id)
        .then(res => {
          this.registerForm.userName = res.data.data.userName
        // console.log(this.registerForm.userName)
          this.registerForm.password = res.data.data.password
          // this.registerForm.sex = res[0].sex
          this.registerForm.phone = res.data.data.phone
          // this.registerForm.email = res[0].email
          // this.registerForm.birth = res[0].birth
          // this.registerForm.introduction = res[0].introduction
          // this.registerForm.location = res[0].location
          // this.registerForm.avator = res[0].avator
        })
        .catch(err => {
          console.log(err)
        })
    },
    goback () {
      this.$router.go(-1)
    },
    saveMsg () {
      // let d = new Date(this.registerForm.birth)
      // let datetime = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate()
      let params = new URLSearchParams()
      params.append('id', this.userId)
      params.append('userName', this.registerForm.userName)
      params.append('password', this.registerForm.password)
      // params.append('sex', this.registerForm.sex)
      params.append('phone', this.registerForm.phone)
      // params.append('email', this.registerForm.email)
      // params.append('birth', datetime)
      // params.append('introduction', this.registerForm.introduction)
      // params.append('location', this.registerForm.location)
     this.$http.post("/user/updateUserMsg",params)
        .then(res => {
          if (res.data.code == 200) {
            this.showError = false
            this.showSuccess = true
            let user=JSON.parse(window.sessionStorage.getItem("user"))
          // console.log(res.data)
          user.userName=res.data.data.userName
          user.password=res.data.data.password
          user.phone=res.data.data.phone
          user.salt=res.data.data.salt
           window.sessionStorage.setItem("user",JSON.stringify(user))
            this.$notify.success({
              title: '编辑成功',
              showClose: true
            })
            setTimeout(function () {
              this.$router.go(-1)
            }, 2000)
          } else {
            this.showSuccess = false
            this.showError = true
            this.$notify.error({
              title: '编辑失败',
              showClose: true
            })
          }
        })
        .catch(err => {
          console.log(err)
        })
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/info.scss';
</style>
