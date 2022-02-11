<template>
<div>
   <the-header/>
  <div class="app-container" style="width:1000px;height:200px;margin-top:120px;margin-left:200px;margin-bottom:100px;">
    <el-form ref="form" :model="form" label-width="110px">
      <el-form-item label="详细地址：" prop="address">
        <el-autocomplete
          v-model="form.address"
          style="width:100%;"
          popper-class="autoAddressClass"
          :fetch-suggestions="querySearchAsync"
          :trigger-on-focus="false"
          placeholder="详细地址(点击地图中该地址出现详细信息)"
          clearable
          @select="handleSelect"
        >
          <template slot-scope="{ item }">
            <i class="el-icon-search fl mgr10" />
            <div style="overflow:hidden;">
              <div class="title">{{ item.title }}</div>
              <span class="address ellipsis">{{ item.address }}</span>
            </div>
          </template>
        </el-autocomplete>
      </el-form-item>
      <el-form-item label="地图定位：">
        <div id="map-container" style="width:100%;height:500px;" />
      </el-form-item>
    </el-form>
  </div>
  <scroll-top/>
  <the-footer style="margin-top:400px;"/>
  </div>
</template>

<script>
/* eslint-disable */
import loadBMap from '../../../src/map.js'
import TheHeader from '../../components/TheHeader.vue'
import TheFooter from '../../components/TheFooter.vue'
import ScrollTop from '@/components/ScrollTop'

export default {
  data() {
    return {
      form: {
        address: '', // 详细地址
        addrPoint: { // 详细地址经纬度
          lng: 0,
          lat: 0
        }
      },
      map: '', // 地图实例
      mk: '', // Marker实例
      locationPoint: null
    }
  },
  components: {
    TheHeader,
    ScrollTop,
    TheFooter
  },
  async mounted() {
    await loadBMap('4jDRKykv7NE0wl0hmx0R5HUyKOoqDDQV') // 加载引入BMap
    this.initMap()
  },
  methods: {
    // 初始化地图
    initMap() {
      var that = this
      // 1、挂载地图
      this.map = new BMap.Map('map-container', { enableMapClick: true })
        this.map.enableScrollWheelZoom(true)//开启鼠标滚轮缩放
       this.map.clearOverlays(); // 清除覆盖物
      var point = new BMap.Point(113.3324436, 23.1315381)
      this.map.centerAndZoom(point, 16)

            // let point = new BMap.Point(116.331398,39.897445);

       // 4、添加（右上角）平移缩放控件
      this.map.addControl(new BMap.NavigationControl({ anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL }))
      // 5、添加（左下角）定位控件
      var geolocationControl = new BMap.GeolocationControl({ anchor: BMAP_ANCHOR_BOTTOM_LEFT })
      geolocationControl.addEventListener('locationSuccess', function(e) {
        that.getAddrByPoint(e.point)
      })
      geolocationControl.addEventListener('locationError', function(e) {
        alert(e.message)
      })
      this.map.addControl(geolocationControl)

        var content =
            '<div style="margin:0;line-height:20px;padding:2px;">' +
            '<img src="../img/baidu.jpg" alt="" style="float:right;zoom:1;overflow:hidden;width:100px;height:100px;margin-left:3px;"/>' +
            "地址：北京市海淀区上地十街10号<br/>电话：(010)59928888<br/>简介：月亮大厦位于北京市海淀区西二旗地铁站附近，为综合研发及办公总部。" +

            "</div>";

        var searchInfoWindow = null;
            searchInfoWindow = new BMapLib.SearchInfoWindow(this.map, content, {
                title: "月亮大厦", //标题
                width: 290, //宽度
                height: 105, //高度
                panel: "panel", //检索结果面板
                enableAutoPan: true, //自动平移
                searchTypes: [
                    BMAPLIB_TAB_SEARCH, //周边检索
                    BMAPLIB_TAB_TO_HERE, //到这里去
                    BMAPLIB_TAB_FROM_HERE, //从这里出发
                ],
            });




      // 3、设置图像标注并绑定拖拽标注结束后事件
         this.mk = new BMap.Marker(point, { enableDragging: true })
        this.map.addOverlay(this.mk)
        this.mk.addEventListener('dragend', function(e) {
        that.getAddrByPoint(e.point)
      })



      // 6、浏览器定位
      this.geolocation()
      // 7、绑定点击地图任意点事件
      this.map.addEventListener('click', function(e) {
        searchInfoWindow.open(this.mk);
        that.getAddrByPoint(e.point)
      })
    },
    // 获取两点间的距离
    getDistancs(pointA, pointB) {
      return this.map.getDistance(pointA, pointB).toFixed(2)
    },
    // 浏览器定位函数
    geolocation() {
      var that = this
      var geolocation = new BMap.Geolocation()
      geolocation.getCurrentPosition(function(res) {
        if (this.getStatus() == BMAP_STATUS_SUCCESS) {
          that.getAddrByPoint(res.point)
          that.locationPoint = res.point
        } else {
          alert('failed' + this.getStatus())
          that.locationPoint = new BMap.Point(113.3324436, 23.1315381)
        }
      }, { enableHighAccuracy: true })
    },
    // 2、逆地址解析函数
    getAddrByPoint(point) {
      var that = this
      var geco = new BMap.Geocoder()
      geco.getLocation(point, function(res) {
        console.log(res)
        that.mk.setPosition(point)
        that.map.panTo(point)
        that.form.address = res.address
        that.form.addrPoint = point
      })
    },
    // 8-1、地址搜索
    querySearchAsync(str, cb) {
      var options = {
        onSearchComplete: function(res) {
          var s = []
          if (local.getStatus() == BMAP_STATUS_SUCCESS) {
            for (var i = 0; i < res.getCurrentNumPois(); i++) {
              s.push(res.getPoi(i))
            }
            cb(s)
          } else {
            cb(s)
          }
        }
      }
      var local = new BMap.LocalSearch(this.map, options)
      local.search(str)
    },
    // 8-2、选择地址
    handleSelect(item) {
      this.form.address = item.address + item.title
      this.form.addrPoint = item.point
      this.map.clearOverlays()
      this.mk = new BMap.Marker(item.point)
      this.map.addOverlay(this.mk)
      this.map.panTo(item.point)
    },
  }
}
</script>

<style lang="scss" scoped>
  .autoAddressClass{
    li {
      i.el-icon-search {margin-top:11px;}
      .mgr10 {margin-right: 10px;}
      .title {
        text-overflow: ellipsis;
        overflow: hidden;
      }
      .address {
        line-height: 1;
        font-size: 12px;
        color: #b4b4b4;
        margin-bottom: 5px;
      }
    }
  }

</style>
