import PageTemplate from "../components/PageTemplate";
import { useState } from "react"; // store user enteried search query

export default function Messages() {
  // store whatever the user types
  const [query, setQuery] = useState("");

  // triggered when user presses "Search" or hits Enter
  const handleSearch = (e: React.FormEvent) => {
    e.preventDefault(); // prevents the page from reloading
    console.log("Searching for:", query);
    // later, you can connect this to your backend or filter posts locally
  };

  return (
    <PageTemplate title="Search">
      <form
        onSubmit={handleSearch}
        className="d-flex align-items-center mb-4"
        style={{ gap: 8 }}
      >
        <input
          type="text"
          placeholder="Search people, posts, events..."
          className="form-control"
          value={query}
          onChange={(e) => setQuery(e.target.value)}
          style={{
            flex: 1,
            border: "2px solid #000",
            borderRadius: 4,
            padding: "8px 12px",
          }}
        />
        <button
          type="submit"
          className="btn btn-dark"
          style={{ background: "#000", color: "#fff" }}
        >
          Search
        </button>
      </form>

      {/* Results placeholder */}
      {query ? (
        <p className="text-muted">
          Showing results for: <strong>{query}</strong>
        </p>
      ) : (
        <p className="text-muted">Type something to start searching.</p>
      )}
    </PageTemplate>
  );
}
