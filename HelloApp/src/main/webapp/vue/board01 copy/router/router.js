import myBoardList from "../components/myBoardList.js";
import myBoardWrite from "../components/myBoardWrite.js";
import myBoardRead from "../components/myBoardRead.js";

export default new VueRouter({
  routes : [
    // main
    {
      path : '/',
      name : 'main',
      component : {
        template : `
        <div>
          <h3>게시판을 불러와주세요</h3>
        </div>
        `,
      }
    },
    // boardList  
    {
      path : '/boardList',
      name : 'boardList',
      component : myBoardList
    },
    // boardRead
    {
      path : '/boardRead/:item',
      name : 'boardRead',
      component : myBoardRead,
      props : true
    },
    // boardWrite
    {
      path : '/boardWrite',
      name : 'boardWrite',
      component : myBoardWrite
    },
    // 기타
    {
      path : '*',
      redirect : '/'
    }
  ]
})