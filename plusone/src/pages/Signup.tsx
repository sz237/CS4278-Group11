export default function Signup() {
  return (
    <section className="min-vh-100 d-flex align-items-center bg-white">
      <div className="container">
        <div className="mx-auto" style={{ maxWidth: 420 }}>
          <h1 className="h3 fw-bold mb-3 text-center">Create your account</h1>
          <form className="vstack gap-3">
            <input
              type="text"
              className="form-control"
              placeholder="Full name"
            />
            <input type="email" className="form-control" placeholder="Email" />
            <input
              type="password"
              className="form-control"
              placeholder="Password"
            />
            <button className="btn btn-primary w-100" type="submit">
              Sign up
            </button>
          </form>
        </div>
      </div>
    </section>
  );
}
