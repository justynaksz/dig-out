import React from 'react'
import { useState, useEffect } from 'react';
import api from '../../../api/axiosConfig.js';
import { DeceasedType } from '../deceased/Deceased.tsx';
import { DeceasedList } from '../deceasedList/DeceasedList.tsx';

export const AllDeceased = () => {
    const [deceasedList, setDeceasedList] = useState<DeceasedType[]>([] as DeceasedType[]);

    const getDeceasedList = async () => {
        try {
            const response = await api.get("/deceased");
            setDeceasedList(response.data);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {getDeceasedList()
    }, []);

    return (
        <div>
            <h4 className='title'> ALL DECEASED </h4>
            <DeceasedList deceasedList={deceasedList}/>
        </div>
    )
}