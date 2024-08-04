import axios from "axios";

interface HandleErrorProps {
  error: unknown;
  message: (error: string | object) => void;
}

function handleError({ error, message }: HandleErrorProps) {
  if (axios.isAxiosError(error)) {
    // The error is an AxiosError object
    if (error.response === undefined) {
      message(error.message);
    } else message(error.response?.data);
  } else {
    // Handle non-Axios error
    message((error as Error).message);
  }
}

export default handleError;