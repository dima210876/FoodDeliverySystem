import {applyMiddleware, createStore} from "redux";
import rootReducer from './reducers';
import { composeWithDevTools } from 'redux-devtools-extension';

const store = createStore(rootReducer, composeWithDevTools( ));

window.store = store

export default store;