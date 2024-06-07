import React from 'react';
import { Deceased, DeceasedType } from '../deceased/Deceased.tsx';

type Props = {
    deceasedList: DeceasedType[];
}

export const DeceasedList = ({deceasedList} : Props) => {
    return (
        <div>
            <ul className='list'>
                {deceasedList?.map((deceased) => (
                    <li key={deceased.id}>
                        <Deceased {...deceased}/>
                    </li>
                ))}
            </ul>
        </div>
    )
}