function BankCarousel() {
  return (
    <div id="bankCarousel" className="carousel slide" data-bs-ride="carousel">
      <div className="carousel-inner">
        <div className="carousel-item active">
          <img
            src="images/carousel1.jpeg"
            // size 800x400
            style={{ width: "500px", height: "400px" }}
            alt="Slide 1"
            className="d-block w-100"
          />
          <div className="carousel-caption bg-info badge text-wrap">
            <h3>Welcome to our Online Banking</h3>
            <p>Convenient and secure way to manage your finances.</p>
          </div>
        </div>
        <div className="carousel-item">
          <img
            src="images/carousel2.jpeg"
            style={{ width: "500px", height: "400px" }}
            alt="Slide 2"
            className="d-block w-100"
          />
          <div className="carousel-caption bg-info badge text-wrap">
            <h3>Easy Fund Transfers</h3>
            <p>Transfer money to anyone, anytime, anywhere.</p>
          </div>
        </div>
        <div className="carousel-item">
          <img
            src="images/carousel3.jpeg"
            style={{ width: "500px", height: "400px" }}
            alt="Slide 3"
            className="d-block w-100"
          />
          <div className="carousel-caption bg-info badge text-wrap">
            <h3>Secure Online Banking</h3>
            <p>
              Your security is our top priority. Rest assured your data is safe.
            </p>
          </div>
        </div>
      </div>

      <button
        className="carousel-control-prev"
        type="button"
        data-bs-target="#bankCarousel"
        data-bs-slide="prev"
      >
        <span className="carousel-control-prev-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Previous</span>
      </button>
      <button
        className="carousel-control-next"
        type="button"
        data-bs-target="#bankCarousel"
        data-bs-slide="next"
      >
        <span className="carousel-control-next-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Next</span>
      </button>
    </div>
  );
}

export default BankCarousel;
