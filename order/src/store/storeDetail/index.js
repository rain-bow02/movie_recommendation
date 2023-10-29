
export default{
        namespaced:true,
        state:{
            items:[],
            total: 0,//总价格
        },
       /**
        * 修改商品数量
        * @param {*} state  需要添加数据的位置
        * @param {*} payload  待添加到购物车中的商品对象
        */
       mutations:{
        // modifyGoodsNum(state,payload){
        //     console.log(state,payload)
            
        //      state.items = state.items.map(prod=>{
        //         const copy ={...prod};
        //         copy.num = payload.num ;
                
        //         console.log(copy)
        //         return copy ;
        //      })
        //    },

           totalMoney(state,payload){
            let total = 0 ;
            const items = state.items ;
            // console.log(payload.id);
            payload.num=payload.num+1;
            total = total + payload.price ;
            // total = parseFloat(total.toFixed(2));
            state.total =  total+state.total;
            console.log( state.total);
        
        },
       }
}