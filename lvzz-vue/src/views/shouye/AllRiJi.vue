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
                   <el-date-picker type="date" placeholder="选择日期" v-model="form.createTime" style="width: 150px;"></el-date-picker>
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
    style="width: 80%;margin-left:80px;margin-top:30px;"
    :default-sort = "{prop: 'date', order: 'descending'}"
    >
    <el-table-column
      prop="date"
      label="日期"
      sortable
      width="180">
    </el-table-column>
    <el-table-column
      prop="name"
      label="姓名"
      sortable
      width="180">
    </el-table-column>
    <el-table-column
      prop="content"
      label="内容"
      :formatter="formatter"
    >
    </el-table-column>

    <el-table-column
          fixed="right"
          label="操作"
          width="180">
        <template slot-scope="scope">
          <el-button @click="editriji(scope.row)" type="primary" size="mini" >修改</el-button>
		  <el-button @click="deleteriji(scope.row)" type="danger" size="mini">删除</el-button>
        </template>
      </el-table-column>
  </el-table>
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
export default {
  data() {
    return {
      pageSize: 10, // 页数
      currentPage: 1, // 当前页
      total:0,
       userId:'',
        form: {
          content: '',
          createTime: '',
        },
         tableData: [{
          date: '2016-05-02',
          name: '王小虎',
          content: '上海市普陀区金沙江路 1518 弄公司的是广东东莞市东莞市感到深深的给江路 1518 弄公司的是广东东莞市东莞市感到深深的给'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          content: '上海市普陀区金沙江路 1517 弄'
        }, {
          date: '2016-05-01',
          name: '王小虎',
          content: '上海市普陀区金沙江路 1519 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          content: '上海市普陀区金沙江路 1516 弄'
        }]
    }
  },
  components: {

  },
  created () {
     this.userId = JSON.parse(window.sessionStorage.getItem("user")).id
     this.queryAllRiJi();
  },
  methods: {
     handleCurrentChange(pageNum){
      this.currentPage=pageNum
      console.log(this.currentPage)
      // this.getAllRiJiList()
    },
     queryAllRiJi() {
       let params = new URLSearchParams();
       params.append("userId",this.userId);
     },
      onSubmit() {
        console.log('submit!');
      },
      formatter(row, column) {
        return row.content;
      },
      editriji(row){

      },
      deleteriji(row){

      },
      queryByInfo(){

      },
      resetForm(){

      }

  }
}
</script>

<style lang="scss"scoped>
</style>
