import { combineReducers } from "redux";
import cartReducer from "./cart";
import { AuthReducer } from "./authReducer";
import { UserDataReducer } from "./userDataReducer"
import {CategoryReducer} from "./categoryReducer";
import {OrderReducer} from "./orderReducer";
import {ReadyOrderReducer} from "./readyOrderReducer";

const rootReducer = combineReducers({
    auth: AuthReducer,
    userData: UserDataReducer,
    cart: cartReducer,
    category: CategoryReducer,
    order: OrderReducer,
    readyOrder: ReadyOrderReducer
});
export default rootReducer;