<template>
  <div>
    <riji-tab></riji-tab>
    <div class="riji">
       <!-- 可以使用 CellGroup 作为容器 -->
          <van-field
      v-model="title"
      name="标题"
      label="标题"
      placeholder="请输入日记标题"
      :rules="[{ required: true, message: '请输入日记标题' }]"
    />

    <van-field name="rate" label="评分">
       <template #input>
          <van-rate v-model="level" allow-half />
        </template>
    </van-field>
     <van-field
    v-model="content"
    rows="2"
    size="large"
    label="日记内容"
    type="textarea"
    maxlength="50"
    placeholder="请输入日记内容"
    show-word-limit
  />
  <van-uploader style="margin-left:20px;" preview-size="120px" :after-read="uploadImg" v-model="fileList" />
   <!-- <div id="summernote"></div> -->
   <van-button style="width:80%;margin-left:40px" @click="addRiJi()" type="primary" size="large" round color="skyblue">
     确认添加
   </van-button>
    </div>
      <tab-footer></tab-footer>
  </div>
</template>

<script type="text/javascript">
import $ from 'jquery';
import TabFooter from '@/components/common/TabFooter.vue'
import { mapGetters } from 'vuex'
import rijiTab from './childComps/rijiTab.vue' //顶部标题栏
import {addRiJi} from '../../network/riji.js'
export default {
  data() {
    return {
       title:'',
       level:0,
       content:'',
       fileList:[],
       pic:'',
       userId:''
    }
  },
  mounted() {
    //  this.summernote()
  },
   computed: {
    ...mapGetters([
      'loginIn', // 登录标识
    ])
  },
  created() {
     
  },
  components: {
     TabFooter,
     rijiTab,
    
  },
  methods: {
    summernote(){
       $('#summernote').summernote({
         height: 160,
         width: 400,
         tabsize: 1,
         placeholder:'请输入内容(仅可上传一张图片)',
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
            // console.log(contents);

            var text = $('<div>'+contents+'</div>').text();
            this.content = text;
            // console.log(this.content);
             },
            onImageUpload: function(files) {
	        	// this.sendFile(files[0]);
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
                  console.log(this.pic);
    	        },
    	    });
	        }
          }
       });
    },
    uploadImg(file,detail){
     let formData = new FormData();
     console.log(file);
      formData.append("files", file.file);
      $.ajax({
    	        data: formData,
    	        type: "POST",
    	        url:"http://localhost:9001/boot/RiJi/upload",  //上传路径
    	        cache: false,
    	        contentType: false,
    	        processData: false,
    	        success: function(res) {
    	            // console.log(res);
                  // this.pic=res.data
                  window.localStorage.setItem("pic",res.data)
    	        },
    	    });
    },
    addRiJi(){
      if(this.loginIn){
        this.userId = JSON.parse(window.sessionStorage.getItem("user")).id
        // console.log(this.fileList);
        this.pic= window.localStorage.getItem("pic")
        console.log(this.pic)
      addRiJi(this.userId,this.title,this.level * 2,this.content,'http://localhost:9001/boot'+this.pic,'该日记为移动端上传').then(res=>{
          // console.log(res)
          this.$toast.success("添加成功!")
          this.fileList=[]
          this.title=''
          this.content=''
          this.level=''
      })
      } else{
          this.$toast.fail("请先登录")
      }
    }
  }
}
</script>

<style lang="less"scoped>
.riji{
  margin-top:50px;
}
</style>
