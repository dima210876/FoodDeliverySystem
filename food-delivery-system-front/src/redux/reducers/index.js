import { combineReducers } from "redux";
import cartReducer from "./cart";
import { AuthReducer } from "./AuthReducer";
import { UserDataReducer } from "./UserDataReducer"

const rootReducer = combineReducers({
    auth: AuthReducer,
    userData: UserDataReducer,
    cart: cartReducer,
});
export default rootReducer;