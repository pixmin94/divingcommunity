export interface Trip {
  tripId: number,
  title: string,
  country: string,
  location: string,
  startDate: Date,
  endDate: Date
}

export interface Account {
  username: string,
  fullName: string,
  email: string,
  nationality: string,
  image: string
}

export interface User {
  username: string,
  password: string
}
