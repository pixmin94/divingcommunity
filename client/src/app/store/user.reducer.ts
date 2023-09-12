import { createReducer, on } from "@ngrx/store";
import * as UserActions from './user.actions';

export interface UserState {
  isLoggedIn: boolean
}

const initialState: UserState = {
  isLoggedIn: false
}

export const userReducer = createReducer(
  initialState,
  on(UserActions.login, (state) => ({ ...state, isLoggedIn: true })),
  on(UserActions.logout, (state) => ({ ...state, isLoggedIn: false}))
)

// const initialState: User = {
//   username: null
// }

// export const userReducer = createReducer(
//   initialState,
//   on(login, (state, {username}) => ({ ...state, username})),
//   on(logout, (state) => ({ ...state, username: null}))
// )
