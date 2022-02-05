<template>
     <div class="Riji" >
    <el-form ref="RiJiform" :model="RiJiform" label-width="80px">
  <el-form-item label="日记内容">
    <div id="summernote">
    </div>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="tijiao()">发表游记</el-button>
    <el-button>取消</el-button>
  </el-form-item>
</el-form>
  </div>
</template>

<script>

import $ from 'jquery';
export default {
  data() {
    return {
      RiJiform:{

       },
       userId:'',
       pic:'',
       content:'',
       result:[]
    }
  },
  components: {

  },
    mounted () {
    this.userId = JSON.parse(window.sessionStorage.getItem("user")).id
    this.summernote()
  },
  methods: {
    summernote(){
       $('#summernote').summernote({
         height: 300,
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
    // sendFile(files){
    //     var data = new FormData();
    // 	    data.append("files", files);
    //       // data.append("userId",this.userId);
    //       this.$http.post("/user/upload",data).then(res=>{
    //         console.log(res)
    //          $('#summernote').summernote('insertImage', res.data);
    //       })
    // },
    tijiao(){
       var text=$("#summernote").summernote('code')
      //  console.log(this.sliceStr(text));


       if($('#summernote').summernote('isEmpty')){
           this.$message.error("内容不能为空!")
       } else{
          let result=this.sliceStr(text);
       let params= new URLSearchParams();
      params.append("userId",this.userId);
      params.append("content",text);
      // console.log(this.pic)
      if(result.length>1){
         params.append("pic",result[1].content);
      }

      params.append("url",text)
      this.$http.post('/RiJi/AddRiJi',params).then(res=>{
         if(res.data.code==200){
             this.$message.success("发表成功!")
             $('#summernote').summernote('code','');
            //  $('#summernote').code('')

         }else{
           this.$message.error("发表失败!请稍后重试")
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
	}
  }
}
</script>

<style lang="scss"scoped>
.Riji{
  width:800px;
  margin-top:50px;
  margin-left: 100px;
}
</style>
