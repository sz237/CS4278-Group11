import { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import Sidebar from "../components/Sidebar";

type Category = "Events" | "Job opportunities" | "Internships" | "Housing";

export default function MakeAPost() {
  const [navOpen, setNavOpen] = useState(false);

  const [title, setTitle] = useState("");
  const [category, setCategory] = useState<Category>("Events");
  const [description, setDescription] = useState("");
  const [file, setFile] = useState<File | null>(null);

  const fileInputRef = useRef<HTMLInputElement | null>(null);
  const navigate = useNavigate();

  const lineColor = "#F2E1C0";

  const onFilePick = () => fileInputRef.current?.click();

  const onSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // TODO: send to backend later
    // console.log({ title, category, description, file });
    navigate("/home");
  };

  const user = (() => {
    try {
      return JSON.parse(localStorage.getItem("user") || "null");
    } catch {
      return null;
    }
  })();

  return (
    <div className="bg-white" style={{ minHeight: "100vh" }}>
      {/* top black header */}
      <header
        className="d-flex align-items-center"
        style={{ height: 65, background: "#000", paddingLeft: 12, gap: 8 }}
      >
        <button
          aria-label="Toggle navigation"
          aria-expanded={navOpen}
          aria-pressed={navOpen}
          onClick={() => setNavOpen((o) => !o)}
          className="btn btn-link p-0 m-0"
          style={{
            lineHeight: 0,
            width: 40,
            height: 40,
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
          }}
        >
          {/* Rotating hamburger */}
          <div className={`hamburger ${navOpen ? "open" : ""}`}>
            <span className="line" />
            <span className="line" />
            <span className="line" />
          </div>
        </button>

        <h1 className="h6 text-light mb-0">Make a post</h1>
      </header>

      {/* slide-in sidebar */}
      <Sidebar isOpen={navOpen} onClose={() => setNavOpen(false)} />

      <main className="container py-4">
        <p className="text-muted mb-3">
          Hi {user?.firstName || "User"}, share an opportunity with the community.
        </p>

        <form className="vstack gap-3" onSubmit={onSubmit}>
          {/* Title */}
          <div className="post-box">
            <input
              type="text"
              className="form-control form-control-lg border-0 bg-transparent post-input"
              placeholder="Title"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              required
            />
          </div>

          {/* Category (radio group) */}
          <div className="post-box">
            <div className="d-flex flex-wrap align-items-center gap-4 px-2 py-1">
              {(["Events", "Job opportunities", "Internships", "Housing", "Other"] as Category[]).map(
                (label) => (
                  <label key={label} className="form-check-inline d-flex align-items-center gap-2 m-0">
                    <input
                      type="radio"
                      className="form-check-input"
                      name="category"
                      value={label}
                      checked={category === label}
                      onChange={() => setCategory(label)}
                    />
                    <span className="post-radio-label">{label}</span>
                  </label>
                )
              )}
            </div>
          </div>

          {/* Description */}
          <div className="post-box">
            <textarea
              className="form-control border-0 bg-transparent post-textarea"
              placeholder="Description"
              rows={6}
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              required
            />
          </div>

          {/* File upload row */}
          <div className="post-box d-flex align-items-center justify-content-between">
            <span className="text-muted ms-2">
              Add files here (photos, flyers, resumes, etc).
              {file ? <strong className="ms-2 text-dark">Selected: {file.name}</strong> : null}
            </span>

            <div className="me-2">
              <input
                ref={fileInputRef}
                type="file"
                className="d-none"
                onChange={(e) => setFile(e.target.files?.[0] || null)}
              />
              <button
                type="button"
                className="btn btn-outline-dark btn-sm px-3"
                onClick={onFilePick}
              >
                + Upload file
              </button>
            </div>
          </div>

          {/* Submit */}
          <div className="d-flex justify-content-end">
            <button 
                type="submit" 
                className="btn btn-dark px-4" 
                style={{ color: "#F2E1C0", fontWeight: "bold" }}>
              Submit
            </button>
          </div>
        </form>
      </main>
    </div>
  );
}