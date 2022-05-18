import { createAsyncThunk } from "@reduxjs/toolkit";
import { bookingApi } from "../api/booking.api";
import { payloadCreator } from "../utils/helper";

export const booking = createAsyncThunk(
  "booking",
  payloadCreator(bookingApi.booking)
);