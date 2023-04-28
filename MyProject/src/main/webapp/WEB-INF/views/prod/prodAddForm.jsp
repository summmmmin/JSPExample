<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="//cdn.ckeditor.com/4.21.0/standard/ckeditor.js"></script>
<script>
  document.addEventListener("DOMContentLoaded", function(){
     CKEDITOR.replace('pdesc', {
     	filebrowserUploadUrl : 'prodUpload.do'
     });
	  
  })
</script>

<section class="py-5">
    <div class="container px-4 px-lg-5 my-5">
        <div class="row gx-4 gx-lg-5 align-items-center">
            <form action="productAdd.do" method="POST">
                <table class="table">
                    <tr>
                        <th>상품코드</th>
                        <td><input type="text" name="pcode" value="P007"></td>
                        <th>평점</th>
                        <td><input type="number" name="like" value="5"></td>
                    </tr>
                    <tr>
                        <th>상품명</th>
                        <td colspan="3"><input type="text" name="pname" value="코스타리카 따라주777"></td>
                    </tr>
                    <tr>
                        <th>상품가격</th>
                        <td><input type="number" name="price" value="3500"></td>
                        <th>할인가격</th>
                        <td><input type="number" name="sprice" value="2500"></td>
                    </tr>
                    <tr>
                        <th>상품설명</th>
                        <td colspan="3"><textarea name="pdesc" cols="100" rows="10"></textarea></td>
                    </tr>
                    <tr>
                        <td colspan="4" align="center">
                            <input type="submit" value="등록">
                            <input type="reset" value="취소">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</section>