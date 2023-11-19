let cartItems = [
  {
    name: "电视",
    quantity: 2,
    price: 2000,
  },
  {
    name: "手机",
    quantity: 1,
    price: 1000,
  },
  {
    name: "音箱",
    quantity: 3,
    price: 500,
  },
];

let discountRate = 0.9;
let totalAmount = 0;

for (let index = 0; index < cartItems.length; index++) {
    let price = cartItems[index].price;
    let quantity = cartItems[index].quantity;
    let tool = 0;
    if(price > 0 && quantity > 0){
        tool = quantity * price;
        totalAmount = totalAmount + tool;
    }
}

alert("优惠后总价为：¥" + totalAmount.toFixed(2))
