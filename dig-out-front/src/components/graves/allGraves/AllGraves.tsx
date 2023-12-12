import React from 'react'
import { useState, useEffect } from 'react';
import api from '../../../api/axiosConfig.js';
import { GraveType } from '../grave/Grave.tsx';
import { GravesList } from '../gravesList/GraveList.tsx';

export const AllGraves = () => {
    const [graves, setGraves] = useState<GraveType[]>([] as GraveType[]);

    const getGraves = async () => {
        try {
            const response = await api.get("/graves");
            setGraves(response.data);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {getGraves()
    }, []);

    return (
        <div>
            <h4 className='title'>ALL GRAVES</h4>
            <GravesList graves={graves}/>
        </div>
    )
}