<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  mounted() {
	      setTimeout(() => {
	        window.L2Dwidget.init({
	          pluginRootPath: 'static/live2dw/',
	          pluginJsPath: 'lib/',

	          pluginModelPath: 'live2d-widget-model-haru_2/assets/',
	          tagMode: false,
	          debug: false,
	          model: { jsonPath: 'static/live2dw/live2d-widget-model-shizuku/assets/shizuku.model.json' },
	          display: { position: 'left', width: 150, height: 300 },
	          mobile: { show: true },
	          log: false
	        })
	      }, 1000)
	    },
}
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;

  color: #2c3e50;
}


</style>
