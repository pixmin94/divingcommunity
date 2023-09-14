export interface Trip {
  tripId: number,
  title: string,
  country: string,
  location: string,
  startDate: Date,
  endDate: Date,
  image: string,
  attendees: string[]
}

export interface Account {
  username: string,
  password: string,
  fullName: string,
  email: string,
  nationality: string
}

export interface User {
  username: string,
  password: string
}
