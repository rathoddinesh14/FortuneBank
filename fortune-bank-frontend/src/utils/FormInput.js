import React from "react";

const FormInput = ({
  label,
  name,
  value,
  onChange,
  validate,
  message,
  blur,
}) => {
  const [error, setError] = React.useState("");

  const handleChange = (e) => {
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

  const handleBlur = (e) => {
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
        onChange={handleChange} // Use handleChange here
        onBlur={handleBlur}
      />
      {error && <div className="invalid-feedback">{error}</div>}
    </div>
  );
};

export default FormInput;
