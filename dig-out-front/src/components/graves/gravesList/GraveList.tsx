import React from 'react';
import { Grave, GraveType } from '../grave/Grave.tsx';

type Props = {
    graves: GraveType[];
}

export const GravesList = ({graves} : Props) => {
    return (
        <div>
            <ul className='list'>
                {graves.map((grave) => (
                    <li key={grave.id}>
                        <Grave {...grave}/>
                    </li>
                ))}
            </ul>
        </div>
    )
}