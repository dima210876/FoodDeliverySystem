import { combineReducers } from "redux";
import { AuthReducer } from "./AuthReducer";
import cartReducer from "./cart";
import {CategoryReducer} from "./categoryReducer";


const rootReducer = combineReducers({
    cart: cartReducer,
    auth: AuthReducer,
    category: CategoryReducer
});

export default rootReducer;