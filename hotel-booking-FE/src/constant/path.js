class Path {
  constructor() {
    this.home = "/";
    this.searchHotel = "/hotel/search";
    this.register = "/register";
    this.login = "/login";
    this.hotelDetail = "/hotel/:id";
    this.bookingDetail = "/booking/:id";
    this.registerMember = "/register-member";
    this.createRoom = "/create-room";
    this.user = "/user";
    this.changePass = "/password";
    this.purchase = "/purchase";

    this.notFound = "*";
  }
}
export const path = new Path();
