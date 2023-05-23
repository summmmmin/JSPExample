export let basket = {
    totalCount: 0,
    totalPrice: 0,
    delCheckedItem: function () {
        console.log('delCheckedItem');
        
        let checked = document.querySelectorAll("input[name='buy']:checked");
        for(let i=0; i<checked.length; i++){
            console.log(checked[i].parentElement.parentElement.parentElement.remove())
        }
    },
    delAllItem: function () {
        console.log('delAllItem');
        document.querySelectorAll('#basket>div.row.data').forEach(function(elem){
            elem.remove();
        });
    },
    reCalc: function () {
        console.log('reCalc');
        this.totalCount = 0;
        this.totalPrice = 0;
        let total = 0;
        let price = 0;
        let pnum=document.querySelectorAll("input[name='p_num1']");
        pnum.forEach(function(p){
            if(p.id != 'p_num'){
                total += Number(p.value);
                price += Number(p.value)*Number(document.querySelector('#p_price'+p.id.slice(-1)).value)
            }
        })
        this.totalCount = total;
        this.totalPrice = price;
    },
    updateUI: function () {
        console.log('updateUI');
        document.querySelector('#sum_p_num').textContent = '상품개수: ' + this.totalCount.formatNumber() + '개'
        document.querySelector('#sum_p_price').textContent = '합계금액: ' + this.totalPrice.formatNumber() + '원'
    },
    changePNum: function (pos) {
        console.log("changePNum");
        console.log(event.target.tagName)
        console.log(event.target.classList.contains('up'))
        let num = document.querySelector('#p_num'+pos).value;
        num = Number(num)+1;
        document.querySelector('#p_num'+pos).value = num;
        document.querySelector('#p_num'+pos).parentElement.parentElement.parentElement.children[2].innerText = num * document.querySelector('#p_price'+pos).value+'원'
    },
    delItem: function (e) {
        console.log('delItem');
        e.parentElement.parentElement.parentElement.remove();
    },
    cartList: function () {
        cartItems.forEach((item, idx) => {
            let template = document.querySelector('#template>div.row.data').cloneNode(true);
            template.querySelector('.img>img').setAttribute('src', '../img/' + item.image)
            template.querySelector('.pname>span').textContent = item.productNm
            template.querySelector('.basketprice>input').value = item.price
            template.querySelector('.basketprice>input').setAttribute('id', 'p_price' + (idx + 1));
            template.querySelector('.basketprice').childNodes[2].textContent = item.price.formatNumber() + "원"
            template.querySelector('.updown>input').value = item.qty
            template.querySelector('.updown>input').setAttribute('value', item.qty)
            template.querySelector('.updown>input').setAttribute('id', 'p_num' + (idx + 1));
            template.querySelector('.sum').textContent = (item.price * item.qty).formatNumber() + "원"

            document.querySelector('#basket').append(template)
        })
    }
};

var cartItems = [{
        no: 1,
        productNm: '이것이 민트가 아니다.',
        qty: 2,
        price: 200,
        image: 'item1.PNG'
    },
    {
        no: 2,
        productNm: '와 아이스크림.',
        qty: 1,
        price: 2000,
        image: 'item2.PNG'
    },
    {
        no: 3,
        productNm: '모나카 케익.',
        qty: 1,
        price: 13600,
        image: 'item3.PNG'
    }
]

// 숫자 3자리 콤마찍기
Number.prototype.formatNumber = function () {
    if (this == 0) return 0;
    let regex = /(^[+-]?\d+)(\d{3})/;
    let nstr = (this + '');
    while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');
    return nstr;
};