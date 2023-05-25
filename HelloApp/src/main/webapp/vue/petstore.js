var APP_LOG_LIFECYCLE_EVENT = false;
let store = new Vue({
  el: '#app',
  data: {
    sitename: 'Vue.js 애완용품샵',
    // product: {
    //   id: 1001,
    //   title: '고양이 사료, 25파운드',
    //   description: "당신의 고양이를 위한 <em>거부할 수 없는</em>, 유기농 사료입니다.",
    //   price: 200,
    //   image: "images/product-fullsize.png",
    //   availableInventory: 5
    // },
    products: [],
    cart: [],
    showProduct: true,
    order: {
      firstName: '',
      lastName: '',
      address: '',
      city: '',
      zip: '',
      state: '',
      method: '자택 주소',
      business: '직장 주소',
      home: '자택 주소',
      gift: '선물로 보내기',
      sendGift: '선물로 보내기',
      dontSendGift: '선물로 보내지 않기'
    },
    states: {
      AL: '알라바마',
      AK: '알래스카',
      AR: '애리조나',
      CA: '캘리포니아',
      NV: '네바다'
    }
  },
  computed: {
    cartItemCount: function(){
      return this.cart.length;
    },
    sortedProducts: function(){
      // {id, title, description, price, image, availableInventory, rating}
      return this.products.sort(function(p1, p2){
        // 오름차순: -1, 1
        //return p1.price - p2.price;
        // 가 < 나
        if (p1.title< p2.title){
          return -1;
        }else{
          return 1;
        }
      })
    }
  },
  methods: {
    addToCart: function(aproduct){
      this.cart.push(aproduct.id);
    },
    showCheckout: function(){
      this.showProduct = this.showProduct ? false : true;
    },
    cartCount: function(id){
      let count = 0;
      for(let i=0; i<this.cart.length; i++){
        if(this.cart[i] == id){
          count++;
        }
      }
      return count;
    },
    canAddToCart: function(aproduct){
      //return false;
      return aproduct.availableInventory > this.cartCount(aproduct.id);
    },
    checkRating: function(n, aproduct){
      return aproduct.rating >= n;
    }
  },
  filters: {
    formatPrice: function(price){
      // 99999: 999.99 2000.00 -> 2,000.00
      if(price > 99999){
        var priceString = (price/100).toFixed(2);
        console.log(priceString)
        var priceAry = priceString.split('').reverse();
        console.log(priceAry);
        var index = 3;
        while(priceAry.length > index+3){
          priceAry.splice(index+3, 0, ',');   // [1,2,3,4].splice(1,0,'5')
          index += 4;
        }
        return '$'+priceAry.reverse().join('');
      } else {
        return '$' + (price/100).toFixed(2);
      }
    }
  },
  beforeCreate: function(){
    if(APP_LOG_LIFECYCLE_EVENT){
      console.log('beforeCreate hook.')
    }
  },
  created: function(){
    if(APP_LOG_LIFECYCLE_EVENT){
      console.log('created hook.')
    
      // fetch('data.json')
      // .then(resolve => resolve.json())
      // .then(result => store.sitename = result.sitename)
      // .catch(err => console.log);

    }
    axios.get('./products.json')
      .then(result => {
        console.log(result);
        this.products = result.data.products;
      })
  },
  beforeMount: function(){
    if(APP_LOG_LIFECYCLE_EVENT){
      console.log('beforeMount hook.')
    }
  },
  mounted: function(){
    if(APP_LOG_LIFECYCLE_EVENT){
      console.log('Mounted hook.')
    }
  },
  beforeUpdate: function(){
    if(APP_LOG_LIFECYCLE_EVENT){
      console.log('beforeUpdate hook.')
    }
  },
  updated: function(){
    if(APP_LOG_LIFECYCLE_EVENT){
      console.log('updated hook.')
      // alert('update')
    }
  },
  beforeDestroy: function(){
    if(APP_LOG_LIFECYCLE_EVENT){
      console.log('beforeDestroy hook.')
    }
  },
  destroyed: function(){
    if(APP_LOG_LIFECYCLE_EVENT){
      console.log('destroyed hook.')
    }
  }
});