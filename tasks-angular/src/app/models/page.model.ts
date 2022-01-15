import { Pageable } from "./pageable.model";

export interface Page<T> {
    content: T[],
    pageable: Pageable | string,
    last: boolean,
    totalPages: number,
    totalElements: number,
    size: number,
    number: number,
    sort: {
        empty: boolean,
        sorted: boolean,
        unsorted: boolean
    },
    first: boolean,
    numberOfElements: number,
    empty: boolean
}