import { Member } from "./member";

export interface ToDo {
    id?: number;
    name: string;
    status: boolean;
    categoryId: number;
    dueDate: Date;
    description: string;
    members: Member[];
}
