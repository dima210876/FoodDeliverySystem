import { useSelector } from "react-redux";
import DrawerItem from "./DrawerItem";

function Drawer(){

    const { totalPrice, totalCount, items } = useSelector(({cart}) => cart );
    const cartItems = useSelector(({ cart }) => cart.items)

    const addedProducts = Object.keys(items).map(key => {
        return items[key][0];
    });

    return(
        <div className="overlay">
            <div className="drawer fixed-top col-md-2 offset-md-9">
                <h5>Card</h5>
                {
                    addedProducts.map((obj) => (
                        <DrawerItem title={obj.title} restaurantName='KFC' price='11.99' inCartCount={cartItems[obj.id] && cartItems[obj.id].length}/>
                    ))
                }
                <h5>{totalCount} items selected</h5>
                <hr/>
                <div className="row">
                <div className="col"><span>Total price</span></div><div className="col total-price"><span ><b>{totalPrice.toFixed(2)}$</b></span> </div> 
                </div>
                
            </div>
        </div>
    );
}

export default Drawer;