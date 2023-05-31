import myHeader from "./components/header.js";
import router from'./router/router.js';

let template = `
  <div>
    <my-header v-on:update-data="updateDataArray" 
    v-bind:dataList="dataArray"></my-header>
    <router-view></router-view>
  </div>
`;

new Vue({
  el : '#app',
  data : {
    dataArray : [],
    boardInfo: {}
  },
  template : template,
  components : {
    'my-header' : myHeader
  },
  methods : {
    updateDataArray : function(inputData){
      this.dataArray = inputData;
    },
    getDataArray : function(){
      return this.dataArray['board'];
    },
    setDataArray : function(boardList){
      this.dataArray['board'] = boardList;
    }
  },
  router  // router : router
})