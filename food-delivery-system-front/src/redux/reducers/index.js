import { combineReducers } from "redux";
import cartReducer from "./cart";
import { AuthReducer } from "./AuthReducer";
import { GetDataReducer } from "./GetDataReducer"

const rootReducer = combineReducers({
    auth: AuthReducer,
    getData: GetDataReducer,
    cart: cartReducer,
});
export default rootReducer;