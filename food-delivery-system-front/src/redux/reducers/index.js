import { combineReducers } from "redux";
import cartReducer from "./cart";
import { AuthReducer } from "./authReducer";
import { UserDataReducer } from "./userDataReducer"
import {CategoryReducer} from "./categoryReducer";

const rootReducer = combineReducers({
    auth: AuthReducer,
    userData: UserDataReducer,
    cart: cartReducer,
    category: CategoryReducer
});
export default rootReducer;