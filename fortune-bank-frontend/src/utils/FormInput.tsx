import React from "react";

interface FormInputProps {
  label: string;
  name: string;
  value: string;
  onChange: (value: string) => void;
  validate: (value: string) => boolean;
  message: string;
  blur: (value: string) => boolean;
}

const FormInput: React.FC<FormInputProps> = ({
  label,
  name,
  value,
  onChange,
  validate,
  message,
  blur,
}) => {
  const [error, setError] = React.useState<string>("");

  const handleChange: React.ChangeEventHandler<HTMLInputElement> = (e) => {
    const inputValue = e.target.value;
    if (name === "email") {
      onChange(inputValue);
    } else {
      if (validate(inputValue) || inputValue === "") {
        onChange(inputValue);
        setError("");
      } else setError(message);
    }
  };

  const handleBlur: React.FocusEventHandler<HTMLInputElement> = (e) => {
    if (blur(e.target.value)) {
      setError("");
    } else {
      setError(message);
    }
  };

  return (
    <div className="form-group">
      <label htmlFor={name}>{label}:</label>
      <input
        {...(name === "email" && { type: "email" })}
        {...(name === "password" && { type: "password" })}
        {...(name === "dob" && { type: "date" })}
        className={`form-control ${error ? "is-invalid" : ""}`}
        id={name}
        name={name}
        value={value}
        onChange={handleChange}
        onBlur={handleBlur}
      />
      {error && <div className="invalid-feedback">{error}</div>}
    </div>
  );
};

export default FormInput;