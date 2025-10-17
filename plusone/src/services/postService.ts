import axios from "axios";

const API_BASE = import.meta.env.VITE_API_URL || "http://localhost:8080/api";

export type Category = "Events" | "Job opportunities" | "Internships" | "Housing";

export type Post = {
  id?: string;
  userId: string;
  category: Category;
  title: string;
  description: string;
  imageUrl?: string | null;
  createdAt?: string;
};

export type ProfileResponse = {
  userId: string;
  firstName: string;
  lastName: string;
  connectionsCount: number;
  requestsCount: number;
  postsCount: number;
  posts: Post[];
};

const api = axios.create({
  baseURL: API_BASE,
  headers: { "Content-Type": "application/json" },
  withCredentials: true,
});

export const postService = {
  async getProfile(userId: string): Promise<ProfileResponse> {
    const { data } = await api.get<ProfileResponse>(`/users/${userId}/profile`);
    return data;
  },
  async list(userId: string): Promise<Post[]> {
    const { data } = await api.get<Post[]>("/posts", { params: { userId } });
    return data;
  },
  async create(post: Post): Promise<Post> {
    const { data } = await api.post<Post>("/posts", post);
    return data;
  },
  async update(id: string, post: Post): Promise<Post> {
    const { data } = await api.put<Post>(`/posts/${id}`, post);
    return data;
  },
  async remove(id: string): Promise<void> {
    await api.delete(`/posts/${id}`);
  },
};