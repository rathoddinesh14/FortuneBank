const FormTemplate = (props) => {
  return (
    <div className="container mt-5">
      <div className="row">
        <div className="col-md-8">{/* Main content in the left column */}</div>
        <div
          className="col-md-4 p-4 rounded shadow"
          style={{ backgroundColor: "rgba(255, 255, 255, 0.9)" }}
        >
          {props.children}
        </div>
      </div>
    </div>
  );
};

export default FormTemplate;
