import {
    basket
} from './basket.js';

document.addEventListener('DOMContentLoaded', function () {

    basket.cartList()
    // 선택상품 삭제.
    document.querySelector('.basketrowcmd a:first-child').addEventListener('click', function () {
        basket.delCheckedItem();
        basket.reCalc();
        basket.updateUI();
    })

    // 장바구니 비우기.
    document.querySelector('.basketrowcmd a:nth-child(2)').addEventListener('click', function () {
        basket.delAllItem();
        basket.reCalc();
        basket.updateUI();
    })

    // 장바구나 삭제 클릭.
    document.querySelectorAll('.basketcmd a').forEach(function (item) {
        item.addEventListener('click', function () {
            basket.delItem(item);
            basket.reCalc();
            basket.updateUI();
        })
    })

    // 수량변경 - 이벤트 종류 구분.
    document.querySelectorAll('.updown').forEach(function (item, idx) {
        item.querySelector('input').addEventListener('keyup', function () {
            basket.changePNum(idx);
            basket.reCalc();
            basket.updateUI();
        })
        item.children[1].addEventListener('click', function () {
            basket.changePNum(idx);
            basket.reCalc();
            basket.updateUI();
        })
        item.children[2].addEventListener('click', function () {
            //basket.changePNum(idx);
            let num = document.querySelector('#p_num'+idx).value;
            if(num<=1){
                num = 1;
            }else{
                num = Number(num)-1;
            }

            document.querySelector('#p_num'+idx).value = num;
            document.querySelector('#p_num'+idx).parentElement.parentElement.parentElement.children[2].innerText = num * document.querySelector('#p_price'+idx).value+'원';
            basket.reCalc();
            basket.updateUI();
        })
    })

    // anchor : 스크롤 탑 차단.
    document.querySelectorAll('a[href="#"]').forEach(function (item) {
        item.setAttribute('href', 'javascript:void(0)');
    })
})