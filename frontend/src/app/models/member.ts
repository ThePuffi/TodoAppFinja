import { ToDo } from "./to-do";

export interface Member {
    id: number;
    username: string;
    firstName?: string;
    lastName?: string;
    password: string;
    email?: string;
    todos?: ToDo[];
}
