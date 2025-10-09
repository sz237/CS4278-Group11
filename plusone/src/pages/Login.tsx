export default function Login() {
  return (
    <section className="min-vh-100 d-flex align-items-center bg-white">
      <div className="container">
        <div className="mx-auto" style={{ maxWidth: 420 }}>
          <h1 className="h3 fw-bold mb-3 text-center">Log in</h1>
          <form className="vstack gap-3">
            <input type="email" className="form-control" placeholder="Email" />
            <input
              type="password"
              className="form-control"
              placeholder="Password"
            />
            <button className="btn btn-primary w-100" type="submit">
              Log in
            </button>
          </form>
        </div>
      </div>
    </section>
  );
}
