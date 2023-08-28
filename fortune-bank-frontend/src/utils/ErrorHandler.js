import axios from "axios";

function handleError(error, message) {
  if (axios.isAxiosError(error)) {
    // The error is an AxiosError object
    if (error.response === undefined) {
      message(error.message);
    } else message(error.response?.data);
  } else {
    // Handle non-Axios error
    message(error.message);
  }
}

export default handleError;
