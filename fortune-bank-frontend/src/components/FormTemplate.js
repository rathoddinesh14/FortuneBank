const FormTemplate = (props) => {
  return (
    <div className="container mt-5">
      <div className="row">
        <div className="col-md-8">{/* Main content in the left column */}</div>
        <div className="col-md-4 bg-white p-4">{props.children}</div>
      </div>
    </div>
  );
};

export default FormTemplate;
