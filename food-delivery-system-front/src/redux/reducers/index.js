import { combineReducers } from "redux";
import { AuthReducer } from "./AuthReducer";
import cartReducer from "./cart";

const rootReducer = combineReducers({
    cart: cartReducer,
    auth: AuthReducer,
});

export default rootReducer;