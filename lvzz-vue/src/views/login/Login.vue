<template>
  <div id="login">
    <!--<img src="./assets/logo.png">-->
    <div class="wrap-banner">
      <div class="main-title" v-show="showTitle">
        <h2>旅游自助平台</h2>
        <p class="subtitle">Welcome to System</p>
        <br />
         <el-button type="text" @click="dialogFormVisible = true,showTitle = false"><h2 style="color: white">Login</h2></el-button>

      </div>
      <vue-particles
        style="position: absolute; width: 100%; height: 100%"
        color="#ffffff"
        :particleOpacity="0.7"
        linesColor="#ffffff"
        :particlesNumber="80"
        shapeType="circle"
        :particleSize="5"
        :linesWidth="2"
        :lineLinked="true"
        :lineOpacity="0.4"
        :linesDistance="150"
        :moveSpeed="3"
        :hoverEffect="true"
        hoverMode="grab"
        :clickEffect="true"
        clickMode="push"
      >
      </vue-particles>
    </div>
    <el-dialog   :visible.sync="dialogFormVisible"
    width=30%
    @close="loginClose()"
    v-show="loginShow"
    >
<h2 style="color: white;">登录</h2>
  <el-form :model="loginForm" ref="loginForm" :rules="rules">
    <el-form-item  prop="name">
      <el-input v-model="loginForm.name" placeholder="请输入用户名" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input v-model="loginForm.password" type="password" placeholder="请输入密码" autocomplete="off" ></el-input>
      <!-- @keyup.enter.native="login('loginForm')" -->
    </el-form-item>
    <el-form-item prop="code">
          <el-input
            size="normal"
            type="text"
            v-model="loginForm.code"
            auto-complete="off"
            placeholder="点击图片更换验证码"
            @keydown.enter.native="login('loginForm')"
            style="width: 180px;float: left;"
          ></el-input>
          <img
            :src="vcUrl"
            @click="updateVerifyCode"
            alt=""
            style="cursor: pointer;margin-left:5px;float:left;height:38px;"
          />
        </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button class="buttoncss" style="float: left;" @click="login('loginForm')">登 录</el-button>
    <el-button class="buttoncss" style="float: left;" @click="chongzhi('loginForm')">重 置</el-button>
    <el-button class="buttoncss" type="primary" @click="regVisible=true,loginShow=false">注 册</el-button>
  </div>
</el-dialog>


 <el-dialog   :visible.sync="regVisible"
    width=30%
   @close="regClose()"
    >
<h2 style="color: white;">注册</h2>
  <el-form :model="regForm" ref="regForm" :rules="Rrules">
    <el-form-item  prop="userName">
      <el-input v-model="regForm.userName" placeholder="请输入用户名" @blur="cheakUsername()" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input v-model="regForm.password" type="password" placeholder="请输入密码" autocomplete="off"></el-input>
    </el-form-item>
      <el-form-item prop="repassword">
      <el-input v-model="regForm.repassword" type="password" @blur="cheakPassword()" placeholder="请确认密码" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item prop="phone">
      <el-input v-model="regForm.phone" type="text"  placeholder="请输入手机号" autocomplete="off"></el-input>
    </el-form-item>
    <!-- <el-form-item prop="role"  style="margin-right:244px;">
      <el-radio-group v-model="regForm.role" size="small">
      <el-radio label="1" class="radioclass" border>管理员</el-radio>
      <el-radio label="0" class="radioclass" border>普通用户</el-radio>
    </el-radio-group>
    </el-form-item> -->
    <el-form-item prop="pic" style="display:table">
     <el-upload
       name="file"
      :action="path+'/user/upload'"
      :on-success="success"
      :limit="1"
      :on-exceed="exceed"
      :file-list="fileList"
      list-type="picture">
  <el-button  size="small" type="primary" style="margin-right:25px;">上传头像</el-button>
  <!-- <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div> -->
</el-upload>
    </el-form-item>
  </el-form>

  <div slot="footer" class="dialog-footer">
    <el-button class="buttoncss" style="float: left;" @click="quxiao('regForm')">取 消</el-button>
    <el-button class="buttoncss" type="primary" @click="reg('regForm')">注 册</el-button>
  </div>
</el-dialog>

  </div>
</template>

<script>
import { mixin } from "../../mixins";


export default {
  name: "login",
  mixins: [mixin],
  data() {
    return {
      showTitle:true,
      loginForm:{
          name:'',
          password:'',
          code: "",
      },
      regForm:{
          userName:'',
          password:'',
          repassword:'',
          phone:'',
          pic:''
      },
      vcUrl: this.path+"/user/verifyCode?time=" + new Date(),
      dialogTableVisible: false,
      dialogFormVisible: false,
      regVisible:false,
      loginShow:true,
      fileList:[],
      isOk:false,//用户名是否合法
      rules:{
         name: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          password:[
            {required:true,message: '请输入密码', trigger: 'blur'},
            {min:6,max:12,message: '密码长度为6~12位',trigger:'blur'}
          ],
          code: [{ required: true, message: "请输入验证码", trigger: "blur" }],
      },
       Rrules:{
         userName: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          password:[
            {required:true,message: '请输入密码', trigger: 'blur'},
            {min:6,max:12,message: '密码长度为6~12位',trigger:'blur'}
          ],
           repassword:[
            {required:true,message: '请输入确认密码', trigger: 'blur'},
            {min:6,max:12,message: '密码长度为6~12位',trigger:'blur'}
          ],//trigger 触发器提示错误信息
          //  role:[
          //   {required:true,message: '请选择角色', trigger: "change"},
          // ],
           pic:[
            {required:true,message: '请选择头像', trigger: 'change'},
          ],
          phone:[
            {required:true,message: '请输入手机号',trigger: 'blur'},
          ]
      },
    }
  },
  methods: {
    loginClose(){
      this.showTitle=true;
      this.$refs['loginForm'].resetFields();
    },
    regClose(){
      this.loginShow=true;
      this.$refs['regForm'].resetFields();
      this.fileList=[];
    },
    success(file,response){
      this.regForm.pic=file.data;
      this.$refs['regForm'].validateField('pic');
    },
    updateVerifyCode() {
      this.vcUrl = this.path+"/user/verifyCode?time=" + new Date();
    },
    login(formName){
         const _this=this;
          let params = new URLSearchParams();
      params.append("userName", this.loginForm.name);
      params.append("password", this.loginForm.password);
      params.append("code", this.loginForm.code);
       this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$http.post('user/queryUser',params).then(res=>{
            if (res.data.code === 3) {
            _this.notify("验证码错误", "error");
            this.vcUrl = this.path+"/user/verifyCode?time=" + new Date();
          }else if(res.data.code == 200){
                window.sessionStorage.setItem("user",res.data.data);
                // console.log(res.data.data);
                this.$router.push('/home');
               this.$message.success("欢迎回来")
            }
            else  if(res.data.code === 4){
              this.$message.error("用户名或密码错误");
              console.log(res.msg);
              this.vcUrl = this.path+"/user/verifyCode?time=" + new Date();
            }

            })
          } else {
            this.$message.error("校验失败")
            return false;
          }
        });
    },
    reg(formName){
          this.$refs[formName].validate((valid) => {
          if (valid) {
              if(this.isOk){
              if(this.regForm.password == this.regForm.repassword){
               this.$http.post('/user/addUser',this.regForm).then(res=>{
             if(res.data.code==200) {
                this.regVisible=false;
                this.fileList=[];
                this.$message.success("注册成功")
             }
              setTimeout(function () {
              _this.$router.push({path: '/'})
            }, 3000)
            })
            }
            else{
              this.$message.error("两次密码不一致，请重新输入")
            }
            }
            else{
              this.$message.error("请更换用户名")
            }
          } else {
            this.$message.error("校验失败")
            return false;
          }
        });
    },
    chongzhi(formName){
      this.$refs[formName].resetFields();
    },
    quxiao(formName){
      this.$refs[formName].resetFields();
      this.fileList=[];
      this.regVisible=false;
    },
    cheakUsername(){
      let userName=this.regForm.userName || null
      this.$http.get('/user/cheakUsername/'+userName).then(res=>{
        if(res.data.code==200){
           this.isOk=true;
        }else{
          this.$message.error("该用户名已被占用");
        }
      })
    },
    cheakPassword(){
      let password=this.regForm.password || null
      let repassword=this.regForm.repassword || null
      if( password != repassword){
         this.$message.error("两次密码不一致，请重新输入")
      }
    },
    exceed(){
      const _this=this
      // this.$message.error("只能上传一张图片")
      _this.notify("只能上传一张图片","error")
    }
  },
};
</script>
// scoped只对本页面起作用
<style scoped>
*,
:after,
:before {
  box-sizing: border-box;
}



html,
body {
  height: 100%;
}

body {
  margin: 0;
  background: #34393f;
}

#login {
  height: 100%;
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

code[class*="language-"],
pre[class*="language-"],
pre,
code {
  font-family: Menlo, Monaco, Andale Mono, Ubuntu Mono, monospace !important;
  font-size: 0.95em;
}

pre.language-js,
pre.language-html {
  max-width: 650px;
  margin: 1rem auto !important;
  background: #292929;
  border-radius: 3px;
  box-shadow: 2px 6px 17px rgba(0, 0, 0, 0.39);
}

.npm-code {
  background: #292929;
  border-radius: 3px;
  box-shadow: 2px 6px 17px rgba(0, 0, 0, 0.39);
}

h1,
h2 {
  font-size: 40px;
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}

h3 {
  font-weight: 100;
  font-size: 2rem;
}

.white {
  color: #ffffff;
}

.teko {
  font-family: "Teko", sans-serif;
}

.text-left {
  text-align: left;
}

.text-center {
  text-align: center;
}

.wrap-banner {
  background: url("../../assets/img/wallhaven-wyme36.jpg")repeat no-repeat;
  background-size:cover;
  position: relative;
  -webkit-align-items: center;
  -ms-flex-align: center;
  align-items: center;
  display: -webkit-flex;
  display: flex;
  height: 100%;
  justify-content: center;
}

.main-section {
  max-width: 650px;
  margin: auto;
  padding: 0 1rem;
}

.main-title {
  color: #fff;
  z-index: 999;
}

.section-title {
  margin: 2rem 0 0 0;
}

.footer {
  margin: 1rem 0;
}

/*  Github button */

.gh-button {
  display: inline-block;
}

.gh-button {
  box-sizing: border-box;
  height: 24px;
  font-weight: 500;
  font-size: 11px;
  line-height: 14px;
  padding: 2px 5px 2px 4px;
  color: #333;
  text-decoration: none;
  text-shadow: 0 1px 0 #fff;
  white-space: nowrap;
  cursor: pointer;
  overflow: hidden;
  border-radius: 3px;
  padding: 3px 10px 3px 8px;
  font-size: 13px;
  line-height: 16px;
  border-radius: 4px;
  background-color: #eee;
  background-image: linear-gradient(#fcfcfc, #eee);
  border: 1px solid #d5d5d5;
}

.gh-button:hover,
.gh-button:focus {
  text-decoration: none;
  background-color: #ddd;
  background-image: linear-gradient(#eee, #ddd);
  border-color: #ccc;
  color: black;
}

.gh-button:active {
  background: #dcdcdc;
  border-color: #b5b5b5;
  box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15);
}

.gh-button__icon {
  display: inline-block;
  vertical-align: top;
  width: 16px;
  height: 16px;
  margin-right: 4px;
  background-image: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz4NCjwhLS0gR2VuZXJhdG9yOiBBZG9iZSBJbGx1c3RyYXRvciAxNy4xLjAsIFNWRyBFeHBvcnQgUGx1Zy1JbiAuIFNWRyBWZXJzaW9uOiA2LjAwIEJ1aWxkIDApICAtLT4NCjwhRE9DVFlQRSBzdmcgUFVCTElDICItLy9XM0MvL0RURCBTVkcgMS4xLy9FTiIgImh0dHA6Ly93d3cudzMub3JnL0dyYXBoaWNzL1NWRy8xLjEvRFREL3N2ZzExLmR0ZCI+DQo8c3ZnIHZlcnNpb249IjEuMSIgaWQ9IkxheWVyXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4Ig0KCSB3aWR0aD0iNDBweCIgaGVpZ2h0PSI0MHB4IiB2aWV3Qm94PSIxMiAxMiA0MCA0MCIgZW5hYmxlLWJhY2tncm91bmQ9Im5ldyAxMiAxMiA0MCA0MCIgeG1sOnNwYWNlPSJwcmVzZXJ2ZSI+DQo8cGF0aCBmaWxsPSIjMzMzMzMzIiBkPSJNMzIsMTMuNGMtMTAuNSwwLTE5LDguNS0xOSwxOWMwLDguNCw1LjUsMTUuNSwxMywxOGMxLDAuMiwxLjMtMC40LDEuMy0wLjljMC0wLjUsMC0xLjcsMC0zLjINCgljLTUuMywxLjEtNi40LTIuNi02LjQtMi42QzIwLDQxLjYsMTguOCw0MSwxOC44LDQxYy0xLjctMS4yLDAuMS0xLjEsMC4xLTEuMWMxLjksMC4xLDIuOSwyLDIuOSwyYzEuNywyLjksNC41LDIuMSw1LjUsMS42DQoJYzAuMi0xLjIsMC43LTIuMSwxLjItMi42Yy00LjItMC41LTguNy0yLjEtOC43LTkuNGMwLTIuMSwwLjctMy43LDItNS4xYy0wLjItMC41LTAuOC0yLjQsMC4yLTVjMCwwLDEuNi0wLjUsNS4yLDINCgljMS41LTAuNCwzLjEtMC43LDQuOC0wLjdjMS42LDAsMy4zLDAuMiw0LjcsMC43YzMuNi0yLjQsNS4yLTIsNS4yLTJjMSwyLjYsMC40LDQuNiwwLjIsNWMxLjIsMS4zLDIsMywyLDUuMWMwLDcuMy00LjUsOC45LTguNyw5LjQNCgljMC43LDAuNiwxLjMsMS43LDEuMywzLjVjMCwyLjYsMCw0LjYsMCw1LjJjMCwwLjUsMC40LDEuMSwxLjMsMC45YzcuNS0yLjYsMTMtOS43LDEzLTE4LjFDNTEsMjEuOSw0Mi41LDEzLjQsMzIsMTMuNHoiLz4NCjwvc3ZnPg0K);
  background-size: contain;
}


/deep/.el-dialog,/deep/.el-input__inner,.buttoncss,.radioclass{
  background-color: transparent;
  color:white;
}

/* .el-dialog__title{
  font-size :28px  !important;
  color:white !important;
} */

</style>
