<template>
  <div id="app">
    <!-- 头部导航 -->
    <header class="header">
      <el-row>
        <el-col :span="24">
          <el-menu default-active="cn" class="el-menu-demo" mode="horizontal" @select="selectNation">
            <el-menu-item index="cn">中国</el-menu-item>
            <el-menu-item index="us">America</el-menu-item>
          </el-menu>
        </el-col>
      </el-row>
    </header>
    <div style="position: relative;height: 60px;width: 100%;"></div>

    <main>
      <!-- 左侧导航 -->
      <div class="main-left">
        <!--
        <el-menu default-active="/Holiday/2017" class="el-menu-vertical-demo" @select="selectYear" :router="true">
          <el-menu-item index="/Holiday/2017" :class="{'isActive': active}">2017</el-menu-item>
          <el-menu-item index="/Holiday/2016" :class="{'isActive': !active}">2016</el-menu-item>
        </el-menu>
        -->
        <ul class="el-menu el-menu-vertical-demo" ref="menuCollapsed">
          <li v-for="(year,index) in years"class="el-submenu">
            <div class="el-submenu__title el-menu-item" style="padding-left: 20px;height: 56px;line-height: 56px;padding: 0 20px;" @click="selectYear(year)">{{ year }}</div>
          </li>
        </ul>
      </div>

      <!-- 右侧主内容区 -->
      <div  class="main-right" >
        <router-view class="view"></router-view>
      </div>
    </main>
  </div>
</template>

<script>
export default {
  name: 'app',
  data: function (){
    return {
      years: [2017,2016]
    }
  },
  methods: {
    selectNation(nation){
      //this.$store.commit('NATION', index);
      this.$store.state.nation = nation;

      var year = this.$store.state.year;
      this.refreshData(nation, year);
    },
    selectYear(year){
      this.$store.state.year = year;

      var nation = this.$store.state.nation;
      this.refreshData(nation, year);
    },
    refreshData(nation, year) {
      this.$router.push({
        path: '/Holiday/'+ nation +'/'+ year
      });
    }
  }
}
</script>

<style>
  body{margin: 0;}
  #app {
    min-width: 1200px;
    margin: 0 auto;
    font-family: "Helvetica Neue","PingFang SC",Arial,sans-serif;
  }
  /* 头部导航 */
  header{z-index: 1000;min-width: 1200px;transition: all 0.5s ease;  border-top: solid 4px #3091F2;  background-color: #fff;  box-shadow: 0 2px 4px 0 rgba(0,0,0,.12),0 0 6px 0 rgba(0,0,0,.04);  }
  header.header-fixed{position: fixed;top: 0;left: 0;right: 0;}
  header .el-menu-demo{padding-left: 300px!important;}

  /* 主内容区 */
  main{    display: -webkit-box;  display: -ms-flexbox;  display: flex;  min-height: 800px;  border: solid 40px #E9ECF1;  background-color: #FCFCFC;  }
  main .main-left{text-align: center;width: 200px;float: left;}
  main .main-right{-webkit-box-flex: 1;  -ms-flex: 1;  flex: 1;  background-color: #fff; padding: 50px 70px; }
  main .el-menu{background-color: transparent!important;}
</style>
