import { ToDo } from "./to-do";

export interface Member {
    id?: number;
    username: string;
    firstname?: string;
    lastname?: string;
    password: string;
    email?: string;
    todos?: ToDo[];
}
