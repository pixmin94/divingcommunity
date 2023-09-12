import { createFeatureSelector, createSelector } from "@ngrx/store";
import { UserState } from './user.reducer';

const selectUserState = createFeatureSelector<UserState>('user');

export const selectIsLoggedIn = createSelector(
  selectUserState,
  (state) => state.isLoggedIn
);
