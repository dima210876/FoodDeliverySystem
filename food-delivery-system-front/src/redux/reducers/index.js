import { combineReducers } from "redux";
import cartReducer from "./cart";
import { AuthReducer } from "./AuthReducer";

const rootReducer = combineReducers({
    auth: AuthReducer,
    cart: cartReducer,
});
export default rootReducer;